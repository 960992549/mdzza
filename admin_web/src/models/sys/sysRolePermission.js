import { parse } from 'qs';
import request from '../../utils/request';
import * as projectUtil from '../../utils/projectUtil';

export default {
  namespace: 'sysRolePermission',
  state: {
    detail: {},
  },
  subscriptions: {

  },
  effects: {

  },
  reducers: {
    setState(state, { payload }) {
      return {...state, ...payload};
    },
  }
};
