-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.
select * from subject
where Credit in (select max(Credit) from subject);

-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
select s.*, m.Mark from subject s 
join mark m on m.SubId = s.SubId
where m.Mark in (select max(Mark) from mark);

-- Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần
select *, avg(m.Mark)from 
student s left join mark m on s.StudentId = m.StudentId
group by s.StudentId