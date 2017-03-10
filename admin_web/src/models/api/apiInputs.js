import { parse } from 'qs';
import request from '../../utils/request';
import * as projectUtil from '../../utils/projectUtil';

const getApiInputsUrl = 'api/apiInput/list';
const configApiInputsUrl = 'api/apiInput/config';

export default {
  namespace: 'apiInputs',
  state: {
    apiId: null,
    list: [],
  },
  subscriptions: {
    setup({ dispatch, history }) {
      history.listen((location) => {
        const exec = new RegExp('/api/apiInputs/([0-9]+$)').exec(location.pathname);
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
      let data = yield request(getApiInputsUrl, parse(payload));
      const list = projectUtil.handleResult(data, false).list;
      yield put({ type: 'setState', payload: { ...payload, list } });
    },
    *save({ payload }, { select, call, put }) {
      const state = yield select(({ apiInputs }) => apiInputs);
      const params = { apiId: state.apiId, inputs: JSON.stringify(state.list)};
      let data = yield request(configApiInputsUrl, parse(params));
      projectUtil.handleResult(data);
    },
  },
  reducers: {
    setState(state, { payload }) {
      return {...state, ...payload};
    },
  }
};
