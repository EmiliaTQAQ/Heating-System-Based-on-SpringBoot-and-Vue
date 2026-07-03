<template>
  <div>
    <div style="margin-left: 700px">
      <el-input v-model="params.user" style="width: 200px" placeholder="意见人"></el-input>
      <el-input v-model="params.state" style="width: 200px; margin-left: 5px" placeholder="意见状态"></el-input>
      <el-button type="warning" round style="margin: 10px" @click="findBySearch()">查询</el-button>
    </div>
    <div style="width: 100%">
      <el-table :data="tableData" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
        <el-table-column prop="content" label="意见内容"></el-table-column>
        <el-table-column prop="account" label="意见人账号"></el-table-column>
        <el-table-column prop="name" label="意见人姓名"></el-table-column>
        <el-table-column prop="datetime" label="意见时间"></el-table-column>
        <el-table-column prop="state" label="意见状态"></el-table-column>
        <el-table-column label="处理">
          <template slot-scope="scope">
            <el-button type="primary" @click="dispose(scope.row)">处理</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
              <el-button slot="reference" type="danger" style="margin-left: 5px">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
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
        user: '',
        state: '',
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
      request.get("/feedback/search", {
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
    dispose(obj) {
      this.form = obj;
      if (this.form.state === "已处理") {
        this.$message.error("意见已处理过")
      } else {
        this.form.state = "已处理"
        request.post("/feedback/update", this.form).then(res => {
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
    del(id) {
      request.delete("/feedback/" + id).then(res => {
        if (res.code === '0') {
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