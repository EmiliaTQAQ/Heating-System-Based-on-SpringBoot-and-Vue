<template>
  <div>
    <div style="margin-bottom: 15px;margin-left: 450px">
      <el-input v-model="params.name" style="width: 200px" placeholder="请输入姓名"></el-input>
      <el-input v-model="params.phone" style="width: 200px; margin-left: 5px" placeholder="请输入电话"></el-input>
      <el-button type="warning" round style="margin: 10px" @click="findBySearch()">查询</el-button>
      <el-button type="warning" round style="margin: 10px" @click="reset()">清空</el-button>
      <el-button type="success" round style="margin: 10px" @click="add()">新增</el-button>
    </div>
    <!--    信息主体-->
    <div style="width: 100%" >
      <el-table :data="tableData"  border :row-class-name="tableRowClassName" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
        <el-table-column label="照片">
          <template v-slot="scope">
            <el-image
                style="width: 70px; height: 70px; border-radius: 50%"
                :src="scope.row.img"
                :preview-src-list="[scope.row.img]">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column prop="account" label="账号"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="sex" label="性别"></el-table-column>
        <el-table-column prop="age" label="年龄"></el-table-column>
        <el-table-column prop="salary" label="工资"></el-table-column>
        <el-table-column prop="phone" label="电话"></el-table-column>
        <el-table-column prop="password" label="密码"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="text" @click="edit(scope.row)"  style="margin-bottom: 10px;width: 70px">编辑</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
              <el-button  slot="reference" type="text" >删除</el-button>
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
      <el-dialog title="请填写信息" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form">
          <el-form-item label="账号" label-width="15%">
            <el-input v-model="form.account" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="姓名" label-width="15%">
            <el-input v-model="form.name" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="头像" label-width="15%">
            <el-upload
                action="http://localhost:8080/files/upload" :on-success="successUpload">
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item label="性别" label-width="15%">
            <el-radio v-model="form.sex" label="男">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
          </el-form-item>
          <el-form-item label="年龄" label-width="15%">
            <el-input v-model="form.age" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="工资" label-width="15%">
            <el-input v-model="form.salary" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="电话" label-width="15%">
            <el-input v-model="form.phone" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="15%">
            <el-input v-model="form.password" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit()">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <div>
      <el-dialog title="请填写信息" :visible.sync="dialogFormVisible1" width="30%">
        <el-form :model="form">
          <el-form-item label="账号" label-width="15%">
            <el-input v-model="form.account" autocomplete="off" style="width: 90%" disabled></el-input>
          </el-form-item>
          <el-form-item label="姓名" label-width="15%">
            <el-input v-model="form.name" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="头像" label-width="15%">
            <el-upload
                action="http://localhost:8080/files/upload" :on-success="successUpload">
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item label="性别" label-width="15%">
            <el-radio v-model="form.sex" label="男">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
          </el-form-item>
          <el-form-item label="年龄" label-width="15%">
            <el-input v-model="form.age" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="工资" label-width="15%">
            <el-input v-model="form.salary" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="电话" label-width="15%">
            <el-input v-model="form.phone" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="15%">
            <el-input v-model="form.password" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="submit1()">确 定</el-button>
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
      params:{
        name:'',
        phone: '',
        pageNum: 1,
        pageSize: 7,
      },
      tableData: [],
      total: 0,
      dialogFormVisible: false,
      dialogFormVisible1: false,
      form:{

      }
    }
  },

  created() {
    this.findBySearch();
  },

  methods: {
    findBySearch() {
      request.get("/heating_engineer/search", {
        params: this.params
      }).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.list;
          console.log(this.tableData)
          this.total = res.data.total;
        } else {
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
      this.dialogFormVisible1 = true;

    },
    reset() {
      this.params = {
        name: '',
        phone: '',
        pageNum: 1,
        pageSize: 5
      }
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
    submit() {
      this.form.role = 'heating_engineer'
      let reg = /^1[0-9]{10}$/;
      if (this.form.age < 100 && this.form.age > 0) {
        if(this.form.salary < 0) {
          this.$message.error("工资错误")
        } else {
          if (!reg.test(this.form.phone)) {
            this.$message.error("手机输入错误")
          } else {
              request.post("/heating_engineer", this.form).then(res => {
                if (res.code === '0') {
                  this.$message({
                    message: '操作成功',
                    type: 'success'
                  });
                  this.dialogFormVisible1 = false;
                  this.findBySearch();
                } else {
                  this.$message({
                    message: res.msg,
                    type: 'error'
                  });
                  console.log(res.msg)
                }
              })
            }
        }
        this.dialogFormVisible = false;
      } else {
        this.$message.error("年龄错误")
      }
    },
    submit1() {
      this.form.role = 'heating_engineer'
      let reg = /^1[0-9]{10}$/;
      if (this.form.age < 100 && this.form.age > 0) {
        if(this.form.balance < 0) {
          this.$message.error("余额错误")
        } else {
          if (!reg.test(this.form.phone)) {
            this.$message.error("手机输入错误")
          } else {
            request.post("/heating_engineer", this.form).then(res => {
              if (res.code === '0') {
                this.$message({
                  message: '操作成功',
                  type: 'success'
                });
                this.dialogFormVisible1 = false;
                this.findBySearch();
              } else {
                this.$message({
                  message: res.msg,
                  type: 'error'
                });
                console.log(res.msg)
              }
            })
          }
        }
        this.dialogFormVisible1 = false;
      } else {
        this.$message.error("年龄错误")
      }
    },
    del(id) {
      request.delete("/heating_engineer/" + id).then(res => {
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
    successUpload(res) {
      this.form.img = res.data
    },
  }
}
</script>
<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>