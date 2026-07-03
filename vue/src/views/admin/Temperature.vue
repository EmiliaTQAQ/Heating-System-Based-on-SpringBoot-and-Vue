<template>
  <div>
    <div style="width: 100%;margin-top: 30px">
      <el-table :data="tableData" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
        <el-table-column prop="account" label="账号"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="address" label="居住地址"></el-table-column>
        <el-table-column prop="temperature" label="温度"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="edit(scope.row)"  style="margin-bottom: 10px;width: 70px">编辑</el-button>
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
    <!--    对话框-->
    <div>
      <el-dialog title="填写温度" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form">
          <el-form-item label="温度" label-width="15%">
            <el-input v-model="form.temperature" autocomplete="off" style="width: 90%"></el-input>
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

export default {
  data() {
    return {
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      params:{
        pageNum: 1,
        pageSize: 7,
      },
      tableData: [],
      total: 0,
      form: {},
      dialogFormVisible: false,
    }
  },
  created() {
    this.findBySearch();
  },
  methods: {
    findBySearch() {
      console.log(this.params)
      request.get("/temperature/search", {
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
    edit(obj) {
      this.form = JSON.parse(JSON.stringify(obj))
      console.log(this.form)
      this.dialogFormVisible = true;
    },
    submit() {
      request.post("/temperature", this.form).then(res => {
        if (res.code === '0') {
          this.$message({
            message: '操作成功',
            type: 'success'
          });
          this.dialogFormVisible = false;
          this.findBySearch();
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
          console.log(res.msg)
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