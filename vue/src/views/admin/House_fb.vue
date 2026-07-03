<template>
    <div>
      <div style="display: flex">
        <div style="flex: 1">
          <div id="bie_cell" style="width: 100%; height: 400px"></div>
        </div>
        <div style="flex: 1">
          <div id="bie_sex" style="width: 100%; height: 400px"></div>
        </div>
      </div>
      <div style="width: 100%">
        <el-table :data="tableData" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
          <el-table-column prop="cell" label="小区"></el-table-column>
          <el-table-column prop="heating_account" label="供暖师傅账号"></el-table-column>
          <el-table-column prop="heating_name" label="供暖师傅姓名"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button type="primary" @click="edit(scope.row)">分配</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <!--    分页-->
      <div style="margin-top: 10px; margin-left: 440px">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="params.pageNum"
            :page-size="params.pageSize"
            :total="total">
        </el-pagination>
      </div>
      <div>
        <el-dialog title="分配供暖师傅" :visible.sync="dialogFormVisible" width="30%">
          <el-form :model="form">

            <el-form-item label="供暖师傅账号" label-width="30%">
              <el-select v-model="form.heating_account" placeholder="请选择" style="width: 30%"  @change="selectChanged">
                <el-option
                    v-for="item in typeObjs"
                    :key="item"
                    :label="item"
                    :value="item">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="供暖师傅姓名" label-width="30%">
                <el-input v-model="form.heating_name" autocomplete="off" style="width: 90%" disabled></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submit()">确 定</el-button>
            <el-button @click="dialogFormVisible = false">取 消</el-button>

          </div>
        </el-dialog>
      </div>
    </div>


</template>

<script>

import request from "@/utils/request";
import * as echarts from 'echarts';
export default {
  name: 'HomeView',
  data() {
    return {
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      sum: '',
      times: '',
      tableData: [],
      dialogFormVisible: false,
      form: {},
      total: 0,
      obj: [],
      params:{
        pageNum: 1,
        pageSize: 5,
      },
      typeObjs: []
    }
  },
  mounted() {
    this.initEcharts_cell()
    this.initEcharts_state()
    this.findBySearch();
    this.findByHeating()
  },
  methods: {
    selectChanged(value) {
      console.log(value)
      request.get("/heating_engineer/findName/" + value).then(res => {
        if (res.code === '0') {
          console.log(res.data + "  123")
          this.form.heating_name= res.data;
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findByHeating() {
      request.get("/heating_engineer").then(res => {
        if (res.code === '0') {
          this.typeObjs = res.data;
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    findBySearch() {
      request.get("/room/search",{
        params:this.params
      }).then(res => {
        if (res.code === '0'){
          this.tableData = res.data.list;
          console.log(this.tableData)
          this.total = res.data.total;
          // console.log(this.tableData[1].password);
        }else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })
    },
    edit(obj) {
      this.form = JSON.parse(JSON.stringify(obj))
      this.dialogFormVisible = true;
    },
    submit() {
      console.log(this.form)
      request.get(`/room/up/${encodeURIComponent(this.form.heating_account)}/${encodeURIComponent(this.form.heating_name)}/${encodeURIComponent(this.form.cell)}`)
          .then(res => {
            if (res.code === '0') {
              this.$message({
                message: '操作成功',
                type: 'success'
              });
              this.dialogFormVisible = false;
              this.findBySearch();
            }
          })
          .catch(error => {
            console.error('请求失败:', error);
            // 可以添加更多错误处理逻辑，如显示错误消息给用户
            this.$message({
              message: '操作失败，请稍后重试',
              type: 'error'
            });
          });

    },
    handleSizeChange(pageSize){
      this.params.pageSize = pageSize;
      this.findBySearch();
    },
    handleCurrentChange(pageNum){
      this.params.pageNum = pageNum;
      this.findBySearch();
    },
    initEcharts_cell() {
      request.get("user/echarts/bie").then(res => {
        if (res.code === '0') {
          this.initBie_cell(res.data)
          console.log(res.data)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    initEcharts_state() {
      request.get("room/echarts/bie1").then(res => {
        if (res.code === '0') {
          //开始渲染数据
          this.initBie_state(res.data)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    initBie_cell(data) {
      var chartDom = document.getElementById('bie_cell');
      var myChart = echarts.init(chartDom);
      var option;
      let xAxisData = [];
      if (data.length > 0) {
        // 遍历 data 数组，提取所需的数据添加到 xAxisData 中
        for (let i = 0; i < data.length; i++) {
          xAxisData.push(data[i].name);
        }
      }
      option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data:  xAxisData,
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        title: {
          text: '住户分布（住状图）',
          subtext: '统计维度：小区',
          left: 'center'
        },
        series: [
          {
            name: '人数',
            type: 'bar',
            barWidth: '60%',
            data: data
          }
        ]
      };

      option && myChart.setOption(option);

    },
    initBie_state(data) {
      let chartDom = document.getElementById('bie_sex');
      let myChart = echarts.init(chartDom);
      let option;

      option = {
        title: {
          text: '性别（饼图）',
          subtext: '统计维度：性别',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        color:['#539841','#123587','#9A60B4'],
        series: [
          {
            name: '缴费状态',
            type: 'pie',
            radius: '50%',
            data: data,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };

      option && myChart.setOption(option);

    }
  }

}
</script>
<style>
.rounded-box {
  border-radius: 10px; /* 设置圆角大小为10像素 */
  width: 200px;
  height: 100px;
  background-color: #f3f3f3;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #333;
}
</style>