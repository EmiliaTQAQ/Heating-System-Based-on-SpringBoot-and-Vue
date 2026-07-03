<template>
  <div>
    <el-upload
        action="http://localhost:8080/room/upload" style="display: inline-block; margin-left: 1050px;margin-top: 20px;margin-bottom: 20px":show-file-list="false" :on-success="successUpload">
      <el-button size="small" type="primary">批量导入</el-button>
    </el-upload>
    <el-button type="info" round style="margin: 10px;margin-left: 170px" @click="hf()">全部恢复</el-button>
    <div style="width: 100%">
      <el-table :data="tableData" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
        <el-table-column prop="cell" label="小区"></el-table-column>
        <el-table-column prop="building" label="楼"></el-table-column>
        <el-table-column prop="gate" label="门牌号"></el-table-column>
        <el-table-column prop="state" label="状态"></el-table-column>
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
    <!--    对话框-->
    <div>
      <el-dialog title="填写房子信息" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form">
          <el-form-item label="小区" label-width="15%">
            <el-input v-model="form.cell" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="楼" label-width="15%">
            <el-input v-model="form.building" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="门牌号" label-width="15%">
            <el-input v-model="form.gate" autocomplete="off" style="width: 90%"></el-input>
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
      request.get("/room/search1",{
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
    hf() {
      request.post('/room/hf').then(res => {
        if (res.code === '0'){
          this.$message.success('操作成功');
          this.findBySearch();
        }else {
          this.$message.error(res.msg);
        }
      })
    },
    submit() {
      request.post("/notice",this.form).then(res => {
        if (res.code === '0'){
          this.$message.success('操作成功');
          this.dialogFormVisible = false;
          this.findBySearch();
        }else {
          this.$message.error(res.msg);
        }
      })
    },
    del(obj) {
      request.delete("/room/" + obj).then(res => {
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
    successUpload(res) {
      if (res.code === '0') {
        if (res.data === "有小区门牌号重复，请勿重复添加") {
          this.$message.error(res.data)
        } else {
          this.$message.success("批量导入成功,建议重新分配供暖师傅");
        }

        this.findBySearch();
      } else {
        this.$message.error(res.msg)
      }
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