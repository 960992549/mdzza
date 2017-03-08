import React from 'react';
import { connect } from 'dva';
import { Form, Icon, Input, Button, Checkbox } from 'antd';

function Login({dispatch, user}) {
  const FormContent = Form.create()(({
    dispatch,
    form: {
      getFieldDecorator,
      validateFields,
      getFieldsValue,
      setFieldsValue,
    }
  }) => {
    function handleSubmit(e) {
      e.preventDefault();
      validateFields((error, values) => {
        if(!error) {
          dispatch({ type: 'user/login', payload: values });
        }
      });
    }
    return (
      <Form onSubmit={handleSubmit}>
        <Form.Item>
          {getFieldDecorator('username', {
            rules: [{ required: true, message: '请输入用户名!' }],
          })(
            <Input addonBefore={<Icon type="user" />} placeholder="用户名" />
          )}
        </Form.Item>
        <Form.Item>
          {getFieldDecorator('password', {
            rules: [{ required: true, message: '请输入密码' }],
          })(
            <Input addonBefore={<Icon type="lock" />} type="password" placeholder="密码" />
          )}
        </Form.Item>
        <Form.Item>
          {getFieldDecorator('remember', {
            valuePropName: 'checked',
            initialValue: true,
          })(
            <Checkbox>Remember me</Checkbox>
          )}
          <Button type="primary" htmlType="submit" className="login-form-button">
            登录
          </Button>
        </Form.Item>
      </Form>
    );
  });
  return (
    <div style={{width: 300, height: 600, margin: "0 auto", paddingTop: 100}}>
      <h1>欢迎登陆</h1>
      <FormContent dispatch={dispatch} />
    </div>
  );
}

// 指定订阅数据
function mapStateToProps({ user }) {
  return {user};
}

export default connect(mapStateToProps)(Login);
