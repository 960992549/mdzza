import fetch from 'dva/fetch';
import * as projectConstant from './projectConstant';

function parseJSON(response) {
  return response.json();
}

function checkStatus(response) {
  if (response.status >= 200 && response.status < 300) {
    return response;
  }
  const error = new Error(response.statusText);
  error.response = response;
  throw error;
}

function checkSuccess(data) {
  if(data && data.code == projectConstant.CODE_SUCCESS) {
    return { isSuccess: true, data: data.data};
  } else {
    let message =  data.text;
    if(data.message) {
      message += "：" + data.message;
    }
    return { isSuccess: false, message };
  }
}

/**
 * Requests a URL, returning a promise.
 *
 * @param  {string} url       The relative URL we want to request
 * @param  {object} [params]  The params we want to pass
 * @return {object}           An object containing either "data" or "err"
 */
export default function request(url, params) {
  let formData = new FormData();
  formData.append(projectConstant.TOKEN_NAME, localStorage.getItem(projectConstant.TOKEN_NAME));
  for(var key in params) {
    formData.append(key, params[key]);
  }
  let options = {
    method: 'post',
    body: formData,
  };
  return fetch(projectConstant.ROOT_URL + url, options)
    .then(checkStatus)
    .then(parseJSON)
    .then(checkSuccess)
    .then(data => ({ data }))
    .catch(error => ({ error }));
}
