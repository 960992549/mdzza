import { parse } from 'qs';
import request from '../../utils/request';
import * as projectUtil from '../../utils/projectUtil';

const getSysRoleUrl = 'sys/sysRole/get';
const saveSysRoleUrl = 'sys/sysRole/save';

export default {
  namespace: 'sysRole',
  state: {
    detail: {},
  },
  subscriptions: {
    setup({ dispatch, history }) {
      history.listen((location) => {
        const exec = new RegExp('/sys/role/([0-9]+$)').exec(location.pathname);
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
      let data = yield request(getSysRoleUrl, parse(payload));
      const detail = projectUtil.handleResult(data, false).detail;
      yield put({ type: 'setState', payload: { detail } });
    },
    *save({ payload }, { call, put }) {
      let data = yield request(saveSysRoleUrl, parse(payload));
      projectUtil.handleResult(data);
    },
  },
  reducers: {
    setState(state, { payload }) {
      return {...state, ...payload};
    },
  }
};
