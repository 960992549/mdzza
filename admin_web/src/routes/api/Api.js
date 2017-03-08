import React from 'react';
import { connect } from 'dva';
import { Form, Input, Button, Select, Radio, Popconfirm, Modal, Spin, Cascader } from 'antd';

function Api({ dispatch, api }) {
  const FormContent = Form.create()(({
    detail,
    dispatch,
    form: {
      getFieldDecorator,
      validateFields,
      getFieldsValue,
      setFieldsValue,
    }
  }) => {
    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 14 },
    };

    function handleSubmit(e) {
      e.preventDefault();
      validateFields((error, values) => {
        if(!error) {
          dispatch({ type: 'api/save', payload: values });
        }
      });
    }

    return (
      <Form onSubmit={handleSubmit} horizontal>
        <Form.Item label="id" style={{display: 'none'}}>
          {getFieldDecorator('id',{
            initialValue: detail.id,
          })(<Input />)}
        </Form.Item>
        <Form.Item label="名称" {...formItemLayout}>
          {getFieldDecorator('name', {
            initialValue: detail.name,
            rules: [{ required: true, message: '请输入名称' }],
          })(
            <Input placeholder="名称" />
          )}
        </Form.Item>
        <Form.Item label="模块" {...formItemLayout}>
          {getFieldDecorator('module', {
            initialValue: detail.module,
            rules: [{ required: true, message: '请输入模块' }],
          })(
            <Input placeholder="模块" />
          )}
        </Form.Item>
        <Form.Item label="资源" {...formItemLayout}>
          {getFieldDecorator('resource', {
            initialValue: detail.resource,
            rules: [{ required: true, message: '请输入资源' }],
          })(
            <Input placeholder="资源" />
          )}
        </Form.Item>
        <Form.Item label="方法" {...formItemLayout}>
          {getFieldDecorator('method', {
            initialValue: detail.method,
            rules: [{ required: true, message: '请输入方法' }],
          })(
            <Input placeholder="方法" />
          )}
        </Form.Item>
        <Form.Item label="调用方法" {...formItemLayout}>
          {getFieldDecorator('invokeMethod', {
            initialValue: detail.invokeMethod,
            rules: [{ required: true, message: '请输入调用方法' }],
          })(
            <Input placeholder="调用方法" />
          )}
        </Form.Item>
        <Form.Item label="说明" {...formItemLayout}>
          {getFieldDecorator('description', {
            initialValue: detail.description,
          })(
            <Input type="textarea" placeholder="说明" />
          )}
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            添加
          </Button>
        </Form.Item>
      </Form>
    );
  });

  return (
    <div>
      <FormContent {...api} dispatch={dispatch} />
    </div>
  );
}

// 指定订阅数据
function mapStateToProps({ api }) {
  return {api};
}

export default connect(mapStateToProps)(Api);
