package com.yrx.datasourcemanager.manager.service;

import com.yrx.datasourcemanager.manager.dao.BlogMapper;
import com.yrx.datasourcemanager.manager.dao.extend.BlogExtMapper;
import com.yrx.datasourcemanager.manager.dto.ArchiveDTO;
import com.yrx.datasourcemanager.manager.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by r.x on 2019/10/13.
 */
@Slf4j
@Service
public class ArchiveService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private BlogExtMapper extMapper;

    public Response<List<ArchiveDTO>> listArchives() {
        List<BlogExtMapper.Archive> list = extMapper.listArchives();
        Map<String, List<ArchiveDTO.ArchiveBlog>> mapping = new TreeMap<>();
        for (BlogExtMapper.Archive archive : list) {
            String year = archive.getYear();
            List<ArchiveDTO.ArchiveBlog> archiveBlogs = mapping.get(year);
            if (archiveBlogs == null) {
                archiveBlogs = new ArrayList<>();
            }
            archiveBlogs.add(new ArchiveDTO.ArchiveBlog(archive.getId(), archive.getTitle()));
            mapping.put(year, archiveBlogs);
        }
        List<ArchiveDTO> dtos = new ArrayList<>();
        for (Map.Entry<String, List<ArchiveDTO.ArchiveBlog>> entry : mapping.entrySet()) {
            ArchiveDTO dto = new ArchiveDTO();
            dto.setYear(entry.getKey());
            dto.setArchiveBlogs(entry.getValue());
            dtos.add(dto);
        }
        return Response.success(dtos);
    }
}
