package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{
    private final JdbcTemplate jdbcTemplate;

//  생성자가 하나면 생략가능
//  @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        // query 자동 생성
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        /*
           테이블이름과 key를 갖고 insert문 생성
           => document 참조!!
        */

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());

        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        //select 결과를 RowMapper로 매핑해야됌
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(),id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(),name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    //db에 있는 데이터 가져와서 객체 생성
    private RowMapper<Member> memberRowMapper(){
//        return new RowMapper<Member>() {
//            @Override
//            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Member member = new Member();
//                member.setId(rs.getLong("id"));
//                member.setName(rs.getString("name"));
//
//                return member;
//            }
//        };
        //람다식 (alt+enter)
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));

            return member;
        };
    }

}
