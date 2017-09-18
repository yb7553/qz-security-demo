/**
 * 
 */
package com.qz.web.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qz.dto.User;
import com.qz.dto.UserQueryCondition;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@GetMapping
	@JsonView(User.UserSimpleView.class)
//	public List<User> query(@RequestParam(name="username",required=true,defaultValue="yhw") String nickname){
//		
//		System.out.println(nickname);
		
	public List<User> query(UserQueryCondition userQueryCondition,@PageableDefault(size=17,page=2,sort="username asc") Pageable pageable){
		
		System.out.println(ReflectionToStringBuilder.toString(userQueryCondition,ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());
		
		
		
		
		List<User> users = new ArrayList<>();
		
		users.add(new User());
		users.add(new User());
		users.add(new User());
		
		
		return users;
	}
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@PathVariable String id){
		User user =new User();
		user.setUsername("yb2");
		return user;
		
	}
}
