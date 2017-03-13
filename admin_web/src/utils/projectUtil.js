import { notification } from 'antd';
import * as projectConstant from './projectConstant';

//出错提示
export function errorTIps(message = '系统异常，请稍后重试') {
  notification['error']({ message: '系统异常', description: message });
}

//操作成功提示
export function successTips(message = '操作成功') {
  notification['success']({ message: '提示', description: message });
}

//操作失败提示
export function failedTips(message = '系统异常，请稍后重试') {
  notification['warning']({ message: '操作失败', description: message });
}

//处理返回结果
export function handleResult(data, needSuccessTips = true) {
  if(data.error) {
    errorTIps()
  } else if(!data.data.isSuccess) {
    failedTips(data.data.message);
  } else {
    if(needSuccessTips) {
      successTips();
    }
    //更新token
    if(data.data.data.token) {
      localStorage.setItem(projectConstant.TOKEN_NAME, data.data.data.token);
    }
    return data.data.data;
  }
}
