<template>
  <div  style="width: 100%;margin-top: 50px;padding: 0;height: 650px">
    <div style="height: 560px">
      <el-table :data="tableData" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
        <el-table-column prop="accessories" label="配件" style="float: left"></el-table-column>
        <el-table-column prop="inventory" label="库存" style="float: left"></el-table-column>
        <el-table-column label="操作" style="float: left">
          <template slot-scope="scope">
            <el-button type="primary" @click="edit(scope.row)">取出</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
              <el-button slot="reference" type="danger" style="margin-left: 5px">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--    分页-->
    <div>
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
      request.get("/accessories/search",{
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
      this.form = obj
      if (  this.form.inventory < 0) {
        this.$message.error("库存不足，请及时补充库存")
      } else {
        this.form.inventory = this.form.inventory - 1
        request.post("/accessories", this.form).then(res => {
          if (res.code === '0') {
            this.$message.success('操作成功');
            this.dialogFormVisible = false;
            this.findBySearch();
          } else {
            this.$message.error(res.msg);
          }
        })
      }

    },
    del(obj) {
      request.delete("/accessories/" + obj).then(res => {
        if (res.code === '0'){
          this.$message({
            message: '已取出',
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

<style>
.el-table th.el-table__cell {
  height: 20px;
  margin: auto;
  padding: 0;
}

</style>