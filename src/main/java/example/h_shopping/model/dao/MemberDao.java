package example.h_shopping.model.dao;

import example.h_shopping.model.dto.MemberDto;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberDao extends DBDao { // JDBC 연동 상속받기
    // 1. 회원 등록
    public int signUp( MemberDto memberDto ){
        try{
            String sql = "insert into member_tbl_02( custno, custname, phone, address, grade, city ) values(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // auto_increment(자동PK)값 결과를 반환 설정
            ps.setInt(1,memberDto.getCustno());
            ps.setString(2,memberDto.getCustname());
            ps.setString(3,memberDto.getPhone());
            ps.setString(4,memberDto.getAddress());
            ps.setString(5,memberDto.getGrade());
            ps.setString(6,memberDto.getCity());
            int count = ps.executeUpdate();
            if( count == 1 ) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    int custno = rs.getInt(1);
                    return custno; // 회원가입 성공한 회원번호 반환
                }
            }
        }catch ( Exception e ){
            System.out.println("[회원등록 Dao] e = " + e);
        }
        return 0;
    }// f e
    
    // 2. 회원 전체 목록
    public List<MemberDto> memberList(){
        List<MemberDto> memberDtos = new ArrayList<>();
        try{
            String sql = "select * from member_tbl_02";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                MemberDto memberDto = new MemberDto();
                memberDto.setCustno(rs.getInt("custno"));
                memberDto.setCustname(rs.getString("custname"));
                memberDto.setPhone(rs.getString("phone"));
                memberDto.setAddress(rs.getString("address"));
                memberDto.setJoindate(rs.getString("joindate"));
                memberDto.setGrade(rs.getString("grade"));
                memberDto.setCity(rs.getString("city"));
                memberDtos.add(memberDto);
            }
            return memberDtos;
        }catch (Exception e){
            System.out.println("[회원목록 Dao] e = " + e);
        }
        return null;
    }// f e

    // 3. 회원 정보 수정
    public boolean updateMember( MemberDto memberDto ){
        try {
            String sql = "update member_tbl_02 set custname = ?, phone = ?, address = ?, grade = ?, city = ? where custno =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getCustname());
            ps.setString(2, memberDto.getPhone());
            ps.setString(3, memberDto.getAddress());
            ps.setString(4, memberDto.getGrade());
            ps.setString(5, memberDto.getCity());
            ps.setInt(6, memberDto.getCustno());
            int count = ps.executeUpdate();
            if( count == 1 ) return true;
        }catch (Exception e){
            System.out.println("[회원수정 Dao]e = " + e);
        }
        return false;
    }// f e

}// class end