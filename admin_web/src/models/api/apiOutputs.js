import { parse } from 'qs';
import request from '../../utils/request';
import * as projectUtil from '../../utils/projectUtil';

const getApiOutputsUrl = 'api/apiOutput/list';
const configApiOutputsUrl = 'api/apiOutput/config';

export default {
  namespace: 'apiOutputs',
  state: {
    apiId: null,
    list: [],
  },
  subscriptions: {
    setup({ dispatch, history }) {
      history.listen((location) => {
        const exec = new RegExp('/api/apiOutputs/([0-9]+$)').exec(location.pathname);
        if(exec) {
          const apiId = exec[1];
          dispatch({
            type: 'init',
            payload: {
              apiId,
            }
          });
        }
      });
    }
  },
  effects: {
    *init({ payload }, { call, put }) {
      let data = yield request(getApiOutputsUrl, parse(payload));
      const list = projectUtil.handleResult(data, false).list;
      yield put({ type: 'setState', payload: { ...payload, list } });
    },
    *save({ payload }, { select, call, put }) {
      const state = yield select(({ apiOutputs }) => apiOutputs);
      const params = { apiId: state.apiId, outputs: JSON.stringify(state.list)};
      let data = yield request(configApiOutputsUrl, parse(params));
      projectUtil.handleResult(data);
    },
  },
  reducers: {
    setState(state, { payload }) {
      return {...state, ...payload};
    },
  }
};
