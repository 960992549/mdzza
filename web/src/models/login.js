export default {
  namespace: 'login',
  state: {
    account: '',
    password: ''
  },
  reducers: {
    login(state, action) {
      return {...state, ...action.payload};
    }
  }
}
