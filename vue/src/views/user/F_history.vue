<template>
  <div>
    <div  style="width: 100%; height: 600px;margin-top: 50px">
      <el-table :data="tableData" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
        <el-table-column prop="content" label="意见内容"></el-table-column>
        <el-table-column prop="datetime" label="提交时间"></el-table-column>
        <el-table-column prop="state" label="提交状态"></el-table-column>
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
        state: '',
        pageNum: 1,
        pageSize: 7,
      },
      tableData: [],
      times: '',
      total: 0,
      dialogFormVisible: false,
      form: {},
    }
  },
  created() {
    this.findBySearch();
  },
  methods: {
    findBySearch() {
      this.params.account = this.user.account
      console.log(this.params)
      request.get("/feedback/searchF", {
        params: this.params
      }).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.list;
          this.total = res.data.total;
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    del(id) {
      request.delete("/feedback/" + id).then(res => {
        if (res.code === '0'){
          this.$message({
            message: '删除成功',
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