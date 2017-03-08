import request from '../utils/request';
import * as projectUtil from '../utils/projectUtil';

const getUserUrl = 'sys/user/get';
const getMenusUrl = 'sys/menu/list';

export default {
  namespace: 'layout',
  state: {
    user: {},
    menus: [],
  },
  subscriptions: {
    /*setup({ dispatch, history }) {
      history.listen((location) => {
        if(location.pathname != '/login' && location.pathname != '/logout') {
          let data = yield request(getUserUrl);
          let user = projectUtil.handleResult(data);
          data = yield request(getMenusUrl);
          let menus = projectUtil.handleResult(data);
        }
      });
    }*/
  },
  effects: {

  },
  reducers: {

  }
}
