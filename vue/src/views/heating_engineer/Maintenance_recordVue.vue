<template>
  <div style="height: 680px">
    <div style="margin-top: 20px;margin-left: 600px">
      <el-input v-model="params.name" style="width: 200px" placeholder="请输入姓名"></el-input>
      <el-button type="warning" round style="margin: 10px" @click="findBySearch()">查询</el-button>
    </div>
    <div style="width: 100%;margin-top: 20px;height: 500px;">
      <el-table :data="tableData" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
        <el-table-column prop="account" label="账号"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="address" label="住址"></el-table-column>
        <el-table-column prop="datetime" label="申请时间"></el-table-column>
        <el-table-column prop="description" label="问题描述"></el-table-column>
        <el-table-column prop="state" label="维修状态"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="dispose(scope.row)">解决</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--    分页-->
    <div style="margin-top: 10px;">
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
        heating_account: '',
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
      this.params.heating_account = this.user.account
      request.get("/maintain/searchH",{
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
    dispose(obj) {
      this.form = obj;
      if (this.form.state === "已解决") {
        this.$message.error("已解决过")
      } else {
        this.form.state = "已解决"
        request.post("/maintain/update", this.form).then(res => {
          if (res.code === '0') {
            this.$message.success('操作成功');
            this.dialogFormVisible = false;
            this.findBySearch();
          } else {
            this.$message.error(res.msg);
          }
        })
      }
      this.findBySearch()
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