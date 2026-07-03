<template>
  <div style="width: 550px;margin: auto">
    <el-card style="height: 685px">
      <el-form :model="form" label-width="80px" size="small" class="login_form">
        <div style="text-align: center" >
          <el-upload
              class="avatar-uploader"
              action="http://localhost:8080/files/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess" style="height: 100px">
            <img v-if="form.img" :src="form.img" class="avatar" >
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
        <el-form-item label="账号" label-width="15%" style="margin-top: 30px">
          <el-input v-model="form.account" autocomplete="off" style="width: 90%" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" label-width="15%">
          <el-input v-model="form.name" autocomplete="off" style="width: 90%" ></el-input>
        </el-form-item>
        <el-form-item label="性别" label-width="15%">
          <el-radio v-model="form.sex" label="男">男</el-radio>
          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
          <el-radio v-model="form.sex" label="女">女</el-radio>
        </el-form-item>
        <el-form-item label="年龄" label-width="15%">
          <el-input v-model="form.age" autocomplete="off" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="家庭住址" label-width="15%">
          <el-input v-model="form.address" autocomplete="off" style="width: 90%" disabled></el-input>
        </el-form-item>
        <el-form-item label="住房面积" label-width="15%">
          <el-input v-model="form.living_space" autocomplete="off" style="width: 90%" disabled></el-input>
        </el-form-item>
        <el-form-item label="余额" label-width="15%">
          <el-input v-model="form.balance" autocomplete="off" style="width: 90%" disabled></el-input>
        </el-form-item>
        <el-form-item label="电话" label-width="15%">
          <el-input v-model="form.phone" autocomplete="off" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="15%">
          <el-input v-model="form.password" autocomplete="off" style="width: 90%"></el-input>
        </el-form-item>
        <div style="text-align: center; padding: 0; " class="btns">
  <div>
      <el-button style="float: left; margin-top: 10px; margin-left: 220px" round type="primary" @click="save()">确 定</el-button>

  </div>

        </div>
      </el-form>
    </el-card>
  </div>
</template>
<script>
import request from "@/utils/request";
export default {
  data() {
    return {
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      form: {},
    }
  },
  created() {
    this.load()
  },
  methods: {

    load() {
      const account = this.user.account;
      if (!account) {
        this.$message.error("当前无法获取用户信息！")
        return
      }
      request.get("/user/message/" + account).then(res => {
        this.form = res.data;
      })
    },
    save() {
      let reg = /^1[0-9]{10}$/;
      if (this.form.age < 100 && this.form.age > 0) {
        if (!reg.test(this.form.phone)) {
          this.$message.error("手机输入错误")
        } else {
          request.post("/user/upMessage", this.form).then(res => {
            if (res.code === '0') {
              this.$emit('transmit', res.data.img)
              this.load()
              this.$emit('refreshUser')
              this.$message.success("修改成功")
            } else {
              this.$message.error("操作失败")
            }
          })
        }
      } else {
        this.$message.error("年龄错误")
      }
    },
    handleAvatarSuccess(res) {
      this.form.img = res.data;
    },
  }
}
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader {
  height: 300px;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 250px;
  height: 150px;
  margin-top: 100px;
  text-align: center;
}
.avatar {
  width: 100px;
  height: 100px;
  display: block;
}

</style>
