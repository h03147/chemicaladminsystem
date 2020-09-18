<template>
  <div class="hello">
    <Index></Index>
  </div>
</template>

<script>
  import Index from '@/views/signin/index.vue'
export default {
  name: 'HelloWorld',
  components: {
    Index
  },
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .hello {
    height: 48.80vw;
    width: 100vw;
  }

</style>
