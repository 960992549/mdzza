import { parse } from 'qs';
import request from '../utils/request';
import * as projectUtil from '../utils/projectUtil';

const loginUrl = 'sys/user/login';

export default {
  namespace: 'user',

  state: {
    username: '',
    password: '',
  },

  subscriptions: {},

  effects: {
    *login({ payload }, { call, put }) {
      let data = yield request(loginUrl, parse(payload));
      projectUtil.handleResult(data);
    },
  },

  reducers: {}
};
