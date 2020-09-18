<template>
  <div id="app">
      <router-view></router-view>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import Index from './views/signin/index'

export default {
  name: 'app'
}
</script>

<style>
  html,body,#app{
    height: 100%!important;
    padding: 0;
    margin: 0;
    overflow-x: hidden;
    overflow-y: auto;
  }
</style>
