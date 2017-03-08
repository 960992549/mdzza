import { parse } from 'qs';
import request from '../utils/request';
import * as projectUtil from '../utils/projectUtil';

const getApiOutputsUrl = 'api/apiOutputs/list';
const saveApiOutputsUrl = 'api/apiOutputs/save';

export default {
  namespace: 'apiOutputs',
  state: {
    list: [],
  },
  subscriptions: {
    setup({ dispatch, history }) {
      history.listen((location) => {
        const exec = new RegExp('/api/apiOutputs/([0-9]+$)').exec(location.pathname);
        if(exec) {
          dispatch({
            type: 'init',
            payload: {
              apiId: exec[1],
            }
          });
        }
      });
    }
  },
  effects: {
    *init({ payload }, { call, put }) {
      let data = yield request(getApiUrl, parse(payload));
      const detail = projectUtil.handleResult(data, false).detail;
      yield put({ type: 'setState', payload: { detail } });
    },
    *save({ payload }, { call, put }) {
      let data = yield request(saveApiUrl, parse(payload));
      projectUtil.handleResult(data);
    },
  },
  reducers: {
    setState(state, action) {   //修改state值
      return { ...state, ...action.payload};
    },
  }
};
