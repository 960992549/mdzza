import React from 'react';
import { connect } from 'dva';
import { Link } from 'dva/router';
import { Row, Col, Input, Form, Button } from 'antd';

function ApiInputs({ dispatch, apiInputs }) {
  const { apiId, list } = apiInputs;

  const FormInputs = Form.create()(({
    form: {
      getFieldDecorator,
      validateFields,
      getFieldsValue,
      setFieldsValue,
    }
  }) => {
    function add() {
      const item = { name: '', label: '', dataType: '', description: '', sort : '' };
      list.push(item);
      dispatch({ type: 'apiInputs/setState', payload: { list }});
    }

    function save(e) {
      e.preventDefault();
      validateFields((error, values) => {
        if(!error) {
          dispatch({ type: 'apiInputs/save', payload: values });
        }
      });
    }

    function del(index) {
      list.splice(index, 1);
      dispatch({ type: 'apiInputs/setState', payload: { list }});
    }

    const Inputs = list.map((data, index) => {
      return (
        <Row key={index}>
          <Col span={4}>
            <Form.Item label="参数名">
              {getFieldDecorator('name_' + index,{
                initialValue: data.name,
              })(<Input />)}
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="标签">
              {getFieldDecorator('label_' + index,{
                initialValue: data.label,
              })(<Input />)}
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="数据类型">
              {getFieldDecorator('dataType_' + index,{
                initialValue: data.dataType,
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
              })(<Input />)}
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item>
              <Link to={"/api/apiInputValidators" + data.id}>配置校验规则</Link>
              <span className="ant-divider" />
              <a onClick={()=>del(index)}>删除</a>
            </Form.Item>
          </Col>
        </Row>
      );
    });
    return (
      <Form inline>
        <Form.Item label="接口编号" style={{display: 'none'}}>
          {getFieldDecorator('apiId',{
            initialValue: apiId,
          })(<Input />)}
        </Form.Item>
        {Inputs}
        <Form.Item>
          <Button onClick={add}>
            添加入参
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
      <FormInputs/>
    </div>
  );
}

function mapStateToProps({ apiInputs }) {
  return { apiInputs };
}

export default connect(mapStateToProps)(ApiInputs);
