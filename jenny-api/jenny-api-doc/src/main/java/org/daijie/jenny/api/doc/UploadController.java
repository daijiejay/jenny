package org.daijie.jenny.api.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.daijie.hadoop.HdfsUtil;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(description="文件上传")
@RestController
public class UploadController {

	@ApiOperation(notes = "hadoop dfs文件上传", value = "hadoop dfs文件上传")
	@RequestMapping(value = "hdfs/upload", method = RequestMethod.POST)
	public ModelResult<String> hdfsUploadFile(@ApiParam(value = "选择文件") MultipartFile file) throws IOException {
		return Result.build(HdfsUtil.upload(file));
	}

}
