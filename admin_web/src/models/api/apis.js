import { parse } from 'qs';
import request from '../../utils/request';
import * as projectUtil from '../../utils/projectUtil';

const getApiListUrl = 'api/api/list';
const delApiUrl = 'api/api/delete';

export default {
  namespace: 'apis',
  state: {
    list: [],
  },
  subscriptions: {
    setup({ dispatch, history }) {
      history.listen((location) => {
        if(location.pathname == '/api/list') {
          dispatch({ type: 'init' });
        }
      });
    }
  },
  effects: {
    *init({ payload }, { call, put}) {
      let data = yield request(getApiListUrl);
      let list = projectUtil.handleResult(data, false).list;
      yield put({ type: 'setState', payload: { list } });
    },
    *del({ payload }, { select, call, put }) {
      let data = yield request(delApiUrl, parse(payload));
      data = projectUtil.handleResult(data);
      if(data) {
        const { list } = yield select(({apis}) => apis);
        for(let i = 0; i < list.length; i++) {
          if(payload.id == list[i].id) {
            list.splice(i, 1);
            yield put({ type: 'setState', payload: { list } });
            return;
          }
        }
      }
    },
  },
  reducers: {
    setState(state, { payload }) {
      return {...state, ...payload};
    },
  }
}
