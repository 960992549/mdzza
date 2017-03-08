import request from '../utils/request';
import * as projectUtil from '../utils/projectUtil';

const getApiListUrl = 'api/apiInfo/list';

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
    }
  },
  reducers: {
    setState(state, action) {   //修改state值
      return { ...state, ...action.payload};
    },
  }
}
