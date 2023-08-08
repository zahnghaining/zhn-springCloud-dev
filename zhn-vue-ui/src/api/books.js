import request from '@/utils/request'

export function createdlist(params) {
  return request({
    url: '/system/books/list',
    method: 'get',
    params
  })

}

export function queryMeBooks(params) {
  return request({
    url: '/system/books/queryMeBooks',
    method: 'post',
    params
  })
}

export function updatemenoy(data) {
  return request({
    url: '/system/books/updatemenoy',
    method: 'post',
    data
  })
}

export function vip(data) {
  return request({
    url: '/system/books/vip',
    method: 'post',
    data
  })
}
/* 下单书籍章数 */

export function placeanorder(data) {
  return request({
    url: '/system/books/placeanorder',
    method: 'post',
    data
  })
}

/* 查看章节数 */
export function lookschapter(data) {
  return request({
    url: '/system/books/lookschapter',
    method: 'post',
    data
  })
}


