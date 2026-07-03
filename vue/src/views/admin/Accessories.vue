<template>
  <div>
    <div>
      <el-button type="warning" round style="margin-left: 750px; margin-top: 20px;margin-bottom: 20px" @click="add()">添加配件</el-button>
    </div>
    <div style="width: 100%">
      <el-table :data="tableData" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
        <el-table-column prop="accessories" label="配件"></el-table-column>
        <el-table-column prop="inventory" label="库存"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="edit(scope.row)">修改</el-button>
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
    <!--    对话框-->
    <div>
      <el-dialog title="填写信息" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form">
          <el-form-item label="配件" label-width="15%">
            <el-input v-model="form.accessories" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="库存" label-width="15%">
            <el-input v-model="form.inventory" autocomplete="off" style="width: 90%"></el-input>
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
    add() {
      this.form = {};
      this.dialogFormVisible = true;
    },
    edit(obj) {
      this.form = JSON.parse(JSON.stringify(obj))
      this.dialogFormVisible = true;
    },
    submit() {
      let pattern = /^\d+$/;

      if ( !pattern.test(this.form.inventory )) {
        this.$message.error('库存错误');
      } else {
        request.post("/accessories",this.form).then(res => {
          if (res.code === '0'){
            this.$message.success('操作成功');
            this.dialogFormVisible = false;
            this.findBySearch();
          }else {
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