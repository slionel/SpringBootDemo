package com.zsj.service;

import com.zsj.dto.HomeworkIsSubmitDTO;
import com.zsj.dto.HomeworkRankDTO;
import com.zsj.entity.Homework;
import com.zsj.entity.UserHomework;
import com.zsj.repository.DTODao;
import com.zsj.repository.HomeworkRepository;
import com.zsj.repository.UserHomeworkRepository;
import com.zsj.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hibernate.criterion.Projections.count;

/**
 * @author zsj55
 */
@Service
public class HomeworkService {
    @Resource
    HomeworkRepository homeworkRepository;
    @Resource
    DTODao dtoDao;
    @Resource
    UserHomeworkRepository userHomeworkRepository;

    public List<Homework> findAll(String type, Date openTime){
        return (List<Homework>) homeworkRepository.findByTypeAndOpenTime(type,openTime);
    }

    public Homework add(Homework homework){
        return homeworkRepository.save(homework);
    }

    public List<Homework> findByDate(Date date){
        return homeworkRepository.findByOpenTime(date);
    }

    public void deleteById(String id){
        homeworkRepository.deleteById(id);
    }

    public List<Homework> findById(List<String> idList){
        return (List<Homework>) homeworkRepository.findAllById(idList);
    }

    public Homework update(Homework homework){
        return homeworkRepository.save(homework);
    }

    public List<HomeworkIsSubmitDTO> getHomeworkDetailByHid(String hid){
        List<HomeworkIsSubmitDTO> dtoList = new ArrayList<HomeworkIsSubmitDTO>();
        List<Map<String, Object>> mapList = dtoDao.queryHomeworkDTOListMap(hid);
        for(int i = 0; i < mapList.size(); i++){
            HomeworkIsSubmitDTO homeworkIsSubmitDTO = new HomeworkIsSubmitDTO();
            String name = (String) mapList.get(i).get("name");
            homeworkIsSubmitDTO.setUserName(name);
            String status = (String) mapList.get(i).get("status");
            homeworkIsSubmitDTO.setStatus(status);
            String homeworkmasterId = (String) mapList.get(i).get("homeworkmaster_id");
            if(homeworkmasterId != null && !"".equals(homeworkmasterId)){
                homeworkIsSubmitDTO.setIsSubmit("已提交");
            }
            else{
                homeworkIsSubmitDTO.setIsSubmit("未提交");
            }
            dtoList.add(homeworkIsSubmitDTO);
        }

        return dtoList;
    }

    public List<HomeworkRankDTO> getHomeworkRankByType(String type){
        int rank = 1;
        List<HomeworkRankDTO> rankDTOList = new ArrayList<HomeworkRankDTO>();
        List<Map<String, Object>> mapList = dtoDao.queryHomeworkRankDTOListMap(type);
        for(int i = 0; i < mapList.size(); i++){

            HomeworkRankDTO homeworkRankDTO = new HomeworkRankDTO();
            String name = (String) mapList.get(i).get("name");
            Long count = (Long) mapList.get(i).get("co");
            String status = (String) mapList.get(i).get("status");
            if("学生".equals(status)){
                homeworkRankDTO.setCount(count);
                homeworkRankDTO.setName(name);
                homeworkRankDTO.setStatus(status);
                homeworkRankDTO.setRank(rank++);
                rankDTOList.add(homeworkRankDTO);
            }
        }
        return rankDTOList;
    }

    public UserHomework addMiddleTable(UserHomework userHomework){
        return userHomeworkRepository.save(userHomework);
    }

    public Page<Homework> pageAll(int page, int limit){
        Pageable pageable = PageRequest.of(page,limit);
        Page<Homework> pageInfo = homeworkRepository.findAll(pageable);
        return pageInfo;
    }
}
