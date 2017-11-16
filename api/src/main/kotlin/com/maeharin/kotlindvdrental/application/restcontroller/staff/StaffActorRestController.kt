package com.maeharin.kotlindvdrental.application.restcontroller.staff

import com.maeharin.kotlindvdrental.application.applicationservice.ActorApplicationService
import com.maeharin.kotlindvdrental.application.restcontroller.staff.resource.ActorResource
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/staff/actors")
@Api(tags = arrayOf("actor"), description = "Actor API")
class StaffActorRestController(
        private val actorApplicationService: ActorApplicationService
) {
    @GetMapping("search")
    @ApiOperation("Actor検索", nickname = "staff_search_actors")
    fun search(
            @RequestParam query: String
    ): List<ActorResource>
            = actorApplicationService.search(query).map(::ActorResource)
}