import request from '@/utils/request'




export function getVehicleInformation(data) {
  return request({
    url: '/system/car/getCarType',
    method: 'post',
    data
  })
}
