import { parse } from 'qs';
import request from '../utils/request';
import * as projectUtil from '../utils/projectUtil';

const getApiInputValidatorsUrl = 'api/apiInputValidator/list';
const saveApiInputValidatorsUrl = 'api/apiInputValidator/save';

export default {
  namespace: 'apiInputValidators',
  state: {
    inputId: null,
    list: [],
  },
  subscriptions: {
    setup({ dispatch, history }) {
      history.listen((location) => {
        const exec = new RegExp('/api/apiInput/apiInputValidators/([0-9]+$)').exec(location.pathname);
        if(exec) {
          const inputId = exec[1];
          dispatch({
            type: 'init',
            payload: {
              inputId,
            }
          });
        }
      });
    }
  },
  effects: {
    *init({ payload }, { call, put }) {
      let data = yield request(getApiInputValidatorsUrl, parse(payload));
      const list = projectUtil.handleResult(data, false).list;
      yield put({ type: 'setState', payload: { ...payload, list } });
    },
    *save({ payload }, { select, call, put }) {
      const state = yield select(({ apiInputValidators }) => apiInputValidators);
      const params = { inputId: state.inputId, validators: JSON.stringify(state.list)};
      let data = yield request(saveApiInputValidatorsUrl, parse(params));
      projectUtil.handleResult(data);
    },
  },
  reducers: {
    setState(state, action) {   //修改state值
      return { ...state, ...action.payload};
    },
  }
};
