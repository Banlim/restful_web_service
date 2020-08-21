package com.example.restfulWebService.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository는 Type을 지정해주어야 한다.
// Repository의 interface를 사용하는 것만으로도 CRUD에 관련된 method를 사용할 수 있다.
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
