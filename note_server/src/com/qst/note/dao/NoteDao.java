package com.qst.note.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.qst.note.bean.NoteBean;
import com.qst.note.util.DBUtil;

public class NoteDao {
	public Boolean insert(String title, String content, String noteTime, String tel) {
		int id = new UserDao().getIDbyTel(tel);
		if (id < 1)
			return false;

		Connection c = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("§å§åyyÄêMMÔÂddÈÕ  HH:mm:ss");
		String nowTime = sdf.format(new Date());

		try {

			PreparedStatement pst = (PreparedStatement) c.prepareStatement(
					"insert into note_table(title, content ,note_time ,user_id, create_time)values(?,?,?,?,?)");
			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, noteTime);
			pst.setInt(4, id);
			pst.setString(5, nowTime);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public NoteBean getNoteByID(int id) {

		NoteBean note = new NoteBean();
		Connection c = DBUtil.getConnection();

		try {
			PreparedStatement pst = (PreparedStatement) c.prepareStatement("select * from note_table where id=?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.first()) {
				note.setId(id);
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setCreateTime(rs.getString("create_time"));
				note.setUpdateTime(rs.getString("update_time"));
				note.setNoteTime(rs.getString("note_ time"));
				note.setUserId(rs.getInt("user_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return note;
	}

	public boolean ModifyNote(int id, String title, String content, String noteTime) {
		if (id < 1)
			return false;

		Connection c = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyFMMFddE HH:mm:ss");
		String nowTime = sdf.format(new Date());

		try {
			PreparedStatement pst = (PreparedStatement) c
					.prepareStatement("update note_table set title=?, content=?,note_ time=?,update time=? where id=?");
			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, noteTime);
			pst.setString(4, nowTime);
			pst.setInt(5, id);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<NoteBean> getAllNotes(String tel) {
		ArrayList<NoteBean> all = new ArrayList<NoteBean>();
		Connection c = DBUtil.getConnection();
		int id = new UserDao().getIDbyTel(tel);
		try {
			PreparedStatement pst = (PreparedStatement) c
					.prepareStatement("select * from note_ table where user_ id=?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NoteBean note = new NoteBean();
				note.setId(rs.getInt("id"));
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString(" content"));
				note.setCreateTime(rs.getString("create_time"));
				note.setUpdateTime(rs.getString("update_time"));
				note.setNoteTime(rs.getString("note_time"));
				note.setUserId(rs.getInt("user_id"));
				all.add(note);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all;
	}

	public boolean deleteById(int id) {
		Connection c = DBUtil.getConnection();

		try {
			PreparedStatement pst = (PreparedStatement) c.prepareStatement("delete from note_ table where id=?");
			pst.setInt(1, id);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
