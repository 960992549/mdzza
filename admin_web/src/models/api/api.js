import { parse } from 'qs';
import request from '../../utils/request';
import * as projectUtil from '../../utils/projectUtil';

const getApiUrl = 'api/api/get';
const saveApiUrl = 'api/api/save';

export default {
  namespace: 'api',
  state: {
    detail: {},
  },
  subscriptions: {
    setup({ dispatch, history }) {
      history.listen((location) => {
        const exec = new RegExp('/api/([0-9]+$)').exec(location.pathname);
        if(exec) {
          dispatch({
            type: 'init',
            payload: {
              id: exec[1],
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
    setState(state, { payload }) {
      return {...state, ...payload};
    },
  }
};
