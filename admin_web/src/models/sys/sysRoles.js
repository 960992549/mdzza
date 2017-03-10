import { parse } from 'qs';
import request from '../../utils/request';
import * as projectUtil from '../../utils/projectUtil';

const getSysRolesUrl = 'sys/sysRole/list';
const delSysRoleUrl = 'sys/sysRole/delete';

export default {
  namespace: 'sysRoles',
  state: {
    list: [],
  },
  subscriptions: {
    setup({ dispatch, history }) {
      history.listen((location) => {
        if(location.pathname == '/sys/role/list') {
          dispatch({ type: 'init' });
        }
      });
    }
  },
  effects: {
    *init({ payload }, { call, put }) {
      const data = yield request(getSysRolesUrl);
      const { list } = projectUtil.handleResult(data, false);
      yield put({ type: 'setState', payload: { list } });
    },
    *del({ payload }, { select, call, put }) {
      let data = yield request(delSysRoleUrl, parse(payload));
      data = projectUtil.handleResult(data);
      if(data) {
        const { list } = yield select(({sysRoles}) => sysRoles);
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
  },
};
