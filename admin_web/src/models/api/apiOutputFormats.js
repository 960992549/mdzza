import { parse } from 'qs';
import request from '../../utils/request';
import * as projectUtil from '../../utils/projectUtil';

const getApiOutputFormatsUrl = 'api/apiOutputFormat/list';
const configApiOutputFormatsUrl = 'api/apiOutputFormat/config';

export default {
  namespace: 'apiOutputFormats',
  state: {
    outputId: null,
    list: [],
  },
  subscriptions: {
    setup({ dispatch, history }) {
      history.listen((location) => {
        const exec = new RegExp('/api/apiOutput/apiOutputFormats/([0-9]+$)').exec(location.pathname);
        if(exec) {
          const outputId = exec[1];
          dispatch({
            type: 'init',
            payload: {
              outputId,
            }
          });
        }
      });
    }
  },
  effects: {
    *init({ payload }, { call, put }) {
      let data = yield request(getApiOutputFormatsUrl, parse(payload));
      const list = projectUtil.handleResult(data, false).list;
      yield put({ type: 'setState', payload: { ...payload, list } });
    },
    *save({ payload }, { select, call, put }) {
      const state = yield select(({ apiOutputFormats }) => apiOutputFormats);
      const params = { outputId: state.outputId, formats: JSON.stringify(state.list)};
      let data = yield request(configApiOutputFormatsUrl, parse(params));
      projectUtil.handleResult(data);
    },
  },
  reducers: {
    setState(state, { payload }) {
      return {...state, ...payload};
    },
  }
};
