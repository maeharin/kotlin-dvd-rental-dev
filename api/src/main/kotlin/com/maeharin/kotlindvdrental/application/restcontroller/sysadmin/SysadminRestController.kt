package com.maeharin.kotlindvdrental.application.restcontroller.sysadmin

import com.maeharin.kotlindvdrental.application.applicationservice.FilmApplicationService
import com.maeharin.kotlindvdrental.application.applicationservice.SysadminApplicationService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sysadmin")
@Api(tags = arrayOf("sysadmin"), description = "システムAdminAPI")
class SysadminRestController(
        private val sysadminApplicationService: SysadminApplicationService
) {
    @PostMapping("/init-all-loginid-and-password")
    @ApiOperation("cusotmerとstaffのloginidとpasswordを初期化する", nickname = "init-all-loginid-and-password")
    fun initAllLoginIdAndPassword()
            = sysadminApplicationService.initAllLoginIdAndPassword()

    @PostMapping("index-to-elasticsearch")
    @ApiOperation("映画ElasticSearchにインデックス", nickname = "index_films_to_elasticsearch")
    fun indexToElasticSearch()
            = sysadminApplicationService.indexToElasticSearch()
}