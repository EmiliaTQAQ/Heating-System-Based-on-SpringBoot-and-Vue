<template>
  <div>
    <div style="margin-left: 550px;margin-bottom: 20px">
      <el-input v-model="params.account" style="width: 200px" placeholder="账号"></el-input>
      <el-input v-model="params.name" style="width: 200px; margin-left: 5px" placeholder="姓名"></el-input>
      <el-button type="warning" round style="margin: 10px" @click="findBySearch()">查询</el-button>
      <el-button type="danger" round style="margin: 10px" @click="del()">全部删除</el-button>
    </div>
    <div style="width: 100%">
      <el-table :data="tableData" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
        <el-table-column prop="account" label="账号"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="datetime" label="缴费时间"></el-table-column>
        <el-table-column prop="paymentAmount" label="缴费金额"></el-table-column>
        <el-table-column prop="paymentCycle" label="缴费周期"></el-table-column>
        <el-table-column prop="address" label="居住地址"></el-table-column>
        <el-table-column prop="living_space" label="房屋面积"></el-table-column>
      </el-table>
    </div>
    <!--    分页-->
    <div  style="margin-top: 10px; text-align: center">
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
        account: '',
        name: '',
        pageNum: 1,
        pageSize: 7,
      },
      tableData: [],
      total: 0,
      form: {},
    }
  },
  created() {
    this.findBySearch();
  },
  methods: {
    findBySearch() {
      console.log(this.params)
      request.get("/pay_the_fees/search", {
        params: this.params
      }).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.list;
          this.total = res.data.total;
          console.log(this.tableData)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    del() {
      request.delete("/pay_the_fees").then(res => {
        if (res.code === '0') {
          request.post("/temperature/del").then(res => {
            this.findBySearch()
          })
          this.$message.success("删除成功")
        } else {
          this.$message.error(res.msg)
        }
      })
      this.findBySearch()
    },
    handleSizeChange(pageSize) {
      this.params.pageSize = pageSize;
      this.findBySearch();
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum;
      this.findBySearch();
    },
  }


}
</script>