import React from 'react';
import { connect } from 'dva';
import { Form, Input, Button, Select, Radio, Popconfirm, Modal, Spin, Cascader } from 'antd';

function SysRole({ dispatch, sysRole }) {
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
    function save(e) {
      e.preventDefault();
      validateFields((error, values) => {
        if(!error) {
          dispatch({ type: 'sysRole/save', payload: values });
        }
      });
    }

    return (
      <Form horizontal>
        <Form.Item label="id" style={{display: 'none'}}>
          {getFieldDecorator('id',{
            initialValue: detail.id,
          })(<Input />)}
        </Form.Item>
        <Form.Item label="英文名称">
          {getFieldDecorator('nameEn', {
            initialValue: detail.nameEn,
            rules: [{ required: true, message: '请输入英文名称' }],
          })(
            <Input placeholder="英文名称" />
          )}
        </Form.Item>
        <Form.Item label="中文名称">
          {getFieldDecorator('nameCn', {
            initialValue: detail.nameCn,
            rules: [{ required: true, message: '请输入中文名称' }],
          })(
            <Input placeholder="中文名称" />
          )}
        </Form.Item>
        <Form.Item label="说明">
          {getFieldDecorator('description', {
            initialValue: detail.description,
          })(
            <Input type="textarea" placeholder="说明" />
          )}
        </Form.Item>
        <Form.Item>
          <Button type="primary" onClick={save}>
            保存
          </Button>
        </Form.Item>
      </Form>
    );
  });

  return (
    <div>
      <FormContent {...sysRole} dispatch={dispatch} />
    </div>
  );
}

// 指定订阅数据
function mapStateToProps({ sysRole }) {
  return {sysRole};
}

export default connect(mapStateToProps)(SysRole);
