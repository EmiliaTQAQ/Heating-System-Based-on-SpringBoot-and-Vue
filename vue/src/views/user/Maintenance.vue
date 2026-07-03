<template>
  <div>
    <div style="width: 100%; height: 600px;margin-top: 50px">
      <el-table :data="tableData" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
        <el-table-column prop="heating_account" label="负责供暖师傅账号"></el-table-column>
        <el-table-column prop="heating_name" label="负责供暖师傅姓名"></el-table-column>
        <el-table-column prop="datetime" label="申请时间"></el-table-column>
        <el-table-column prop="description" label="问题描述"></el-table-column>
        <el-table-column prop="state" label="维修状态"></el-table-column>
      </el-table>
    </div>
    <!--    分页-->
    <div style="margin-top: 10px">
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
        account:'',
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
      this.params.account = this.user.account
      request.get("/maintain/searchU",{
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