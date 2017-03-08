import React from 'react';
import { connect } from 'dva';
import { Link } from 'dva/router';
import { Row, Col, Input, Form, Button, InputNumber } from 'antd';

function ApiInputValidators({ dispatch, apiInputValidators }) {
  const { list } = apiInputValidators;

  const FormValidators = Form.create()(({
    form: {
      getFieldDecorator,
      validateFields,
      getFieldsValue,
      getFieldValue,
      setFieldsValue,
    }
  }) => {
    function add() {
      changeList();
      list.push({ rule: '', message: '', description: '', sort : '' });
      dispatch({ type: 'apiInputValidators/setState', payload: { list }});
    }

    function save(e) {
      e.preventDefault();
      validateFields((error, values) => {
        if(!error) {
          changeList();
          dispatch({ type: 'apiInputValidators/setState', payload: { list }});
          dispatch({ type: 'apiInputValidators/save', payload: values });
        }
      });
    }

    function del(index) {
      changeList();
      list.splice(index, 1);
      dispatch({ type: 'apiInputValidators/setState', payload: { list }});
    }

    //修改list的值为form表单中的数据
    function changeList() {
      const fields = ['rule', 'message', 'description', 'sort'];
      for(let i = 0; i < list.length; i++) {
        for(let j = 0; j < fields.length; j++) {
          list[i][fields[j]] = getFieldValue(fields[j] + "_" + i);
        }
      }
    }

    const Validators = list.map((data, index) => {
      return (
        <Row key={index}>
          <Col span={4}>
            <Form.Item label="校验规则">
              {getFieldDecorator('rule_' + index,{
                initialValue: data.rule,
                rules: [{ required: true, message: '请输入校验规则' }],
              })(<Input />)}
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="提示信息">
              {getFieldDecorator('message_' + index,{
                initialValue: data.message,
                rules: [{ required: true, message: '请输入提示信息' }],
              })(<Input />)}
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="说明">
              {getFieldDecorator('description_' + index,{
                initialValue: data.description,
              })(<Input />)}
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="排序">
              {getFieldDecorator('sort_' + index,{
                initialValue: data.sort,
                rules: [{ required: true, message: '请输入排序' }],
              })(<InputNumber min={0} step={10} />)}
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item>
              <a onClick={()=>del(index)}>删除</a>
            </Form.Item>
          </Col>
        </Row>
      );
    });

    return (
      <Form inline>
        {Validators}
        <Form.Item>
          <Button onClick={add}>
            添加校验规则
          </Button>
          <Button onClick={save}>
            保存配置
          </Button>
        </Form.Item>
      </Form>
    );
  });

  return (
    <div>
      <FormValidators/>
    </div>
  );
}

function mapStateToProps({ apiInputValidators }) {
  return { apiInputValidators };
}

export default connect(mapStateToProps)(ApiInputValidators);
