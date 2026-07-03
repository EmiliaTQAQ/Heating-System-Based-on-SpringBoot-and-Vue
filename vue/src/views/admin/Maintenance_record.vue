<template>
  <div>
    <div style="margin-left: 850px">
      <el-input v-model="params.name" style="width: 200px" placeholder="请输入姓名"></el-input>
      <el-button type="warning" round style="margin: 10px" @click="findBySearch()">查询</el-button>
    </div>
    <div style="width: 100%">
      <el-table :data="tableData" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
        <el-table-column prop="account" label="账号"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="address" label="住址"></el-table-column>

        <el-table-column prop="datetime" label="申请时间"></el-table-column>
        <el-table-column prop="description" label="问题描述"></el-table-column>
        <el-table-column prop="heating_name" label="维修师傅"></el-table-column>
        <el-table-column prop="state" label="维修状态"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
              <el-button slot="reference" type="danger" style="margin-left: 5px">删除</el-button>
            </el-popconfirm>
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
  </div>
</template>
<script>
import request from "@/utils/request";
export default {
  data() {
    return {
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      params:{
        name:'',
        pageNum: 1,
        pageSize: 7,
      },
      tableData: [],
      dialogFormVisible: false,
      form: {},
      total: 0,
      obj: []
    }
  },
  created() {
    this.findBySearch();
  },
  methods: {
    findBySearch() {
      request.get("/maintain/search",{
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
    del(obj) {
      request.delete("/maintain/" + obj).then(res => {
        if (res.code === '0'){
          this.$message({
            message: '已删除',
            type: 'success'
          });
          this.findBySearch();
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })
    },
    handleSizeChange(pageSize){
      this.params.pageSize = pageSize;
      this.findBySearch();
    },
    handleCurrentChange(pageNum){
      this.params.pageNum = pageNum;
      this.findBySearch();
    },
  }
}
</script>