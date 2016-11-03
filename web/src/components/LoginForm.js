import React from 'react';
import { Form, Icon, Input, Button, Checkbox } from 'antd';
import styles from './LoginForm.css';

const FormItem = Form.Item;

const LoginForm = Form.create()(React.createClass({
  handleSubmit(e) {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if(err) {
        return;
      }
      console.log("received values of form;" + values);
    });
  },
  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <Form onSubmit={this.handleSubmit} className={styles.login_form}>
        <FormItem>
          {
            getFieldDecorator('account', {
              rules: [{required: true, message: '请输入账户名'}]
            })(
              <Input addonBefore={<Icon type="user" />} placeholder="用户名/手机号/邮箱" />
            )
          }
        </FormItem>
        <FormItem>
          {
            getFieldDecorator('password', {
              rules: [{required: true, message: '请输入登录密码'}]
            })(
              <Input addonBefore={<Icon type="lock" />} placeholder="登录密码" />
            )
          }
        </FormItem>
        <FormItem>
          {
            getFieldDecorator('remember', {
              valuePropName: 'checked',
              initialValue: true
            })(
              <Checkbox>记住我</Checkbox>
            )
          }
          <a className={styles.login_form_forgot}>忘记密码</a>
          <Button type="primary" htmlType="submit" className={styles.login_form_button}>登录</Button>
          <a>注册</a>
        </FormItem>
      </Form>
    );
  }
}));

export default LoginForm;
