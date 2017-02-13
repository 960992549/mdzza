import { parse } from 'qs';
import { routerRedux } from 'dva/router';
const Picture = {

  namespace: 'picture',

  state: {
    id: null,
    name: null,
    url: null,
  },
  subscriptions: {
    setup({ dispatch, history }) {
      history.listen((location) => {
        console.log("history location")
        console.log(location);
        console.log(history);
      });
    },
  },
  effects: {
    *getPicture({ payload }, { put, call, select }) {
      const data =  {id: payload.id, name: '经典', url: ''};
      yield put({
        type: 'initPicture',
        payload: data,
      });
    },
  },
  reducers: {
    initPicture(state, action) {
      return {...state, ...action.payload};
    },
  },
};

export default Picture;
