package com.example.studentapi.service;

import com.example.studentapi.dao.StudentDAO;
import com.example.studentapi.model.Student;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/students")
public class StudentService {
    private StudentDAO studentDAO = new StudentDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(Student student) {
        try {
            studentDAO.addStudent(student);
            return Response.status(Response.Status.CREATED).entity("Student added successfully.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") int id) {
        try {
            Student student = studentDAO.getStudentById(id);
            if (student != null) {
                return Response.ok(student).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Student not found.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(Student student) {
        try {
            studentDAO.updateStudent(student);
            return Response.ok("Student updated successfully.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudent(@PathParam("id") int id) {
        try {
            studentDAO.deleteStudent(id);
            return Response.ok("Student deleted successfully.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: " + e.getMessage()).build();
        }
    }
}
