<template>
  <div class="scroll-announcement">
    <div class="announcement-container">
      <div class="announcement-item" v-for="(item, index) in noticeList" :key="index">
        {{ item.datetime }} - {{ item.content }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ScrollAnnouncement',
  props: {
    noticeList: {
      type: Array,
      required: true
    }
  }
}
</script>

<style scoped>
.scroll-announcement {
  overflow: hidden;
  white-space: nowrap;
  box-sizing: border-box;
}

.announcement-container {
  display: inline-block;
  animation: scroll-announcement 10s linear infinite;
}

.announcement-item {
  display: inline-block;
  padding: 5px 10px;
  font-size: 16px;
}

@keyframes scroll-announcement {
  0% {
    transform: translateX(100%);
  }
  100% {
    transform: translateX(-100%);
  }
}
</style>