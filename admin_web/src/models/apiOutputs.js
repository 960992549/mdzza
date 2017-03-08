import { parse } from 'qs';
import request from '../utils/request';
import * as projectUtil from '../utils/projectUtil';

const getApiOutputsUrl = 'api/apiOutput/list';
const saveOutputsUrl = 'api/apiOutput/save';

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
            type: 'setState',
            payload: {
              apiId,
            }
          });
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
      yield put({ type: 'setState', payload: { list } });
    },
    *save({ payload }, { select, call, put }) {
      const state = yield select(({ apiOutputs }) => apiOutputs);
      const params = { apiId: state.apiId, outputs: JSON.stringify(state.list)};
      let data = yield request(saveOutputsUrl, parse(params));
      projectUtil.handleResult(data);
    },
  },
  reducers: {
    setState(state, action) {   //修改state值
      return { ...state, ...action.payload};
    },
  }
};
