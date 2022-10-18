package com.ahau.vol.controller;

import com.ahau.vol.common.Code;
import com.ahau.vol.common.Result;
import com.ahau.vol.domain.BlastParam;
import com.ahau.vol.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Vector;

@RestController
@RequestMapping("/exec")
public class TrainController {

    // 调用trainService
    @Autowired
    private TrainService trainService;

    /**
     * @Description: 使用参数文件训练 1. 接受表单 2. 运行数据
     * @Param: FormStart
     * @Return: Result
     */
    @GetMapping("/trainFile")
    public Result trainFile(HttpServletRequest request) {
        // 1. 从Session获取数据
        System.out.println("=========Controller：开始训练，读取Session中，文件属性===========");
        HttpSession session = request.getSession();
        String trainUrl = (String) session.getAttribute("train_Url");
        System.out.println("trainUrl:" + trainUrl);
        String paramUrl = (String) session.getAttribute("param_Url");
        System.out.println("ParamUrl:" + paramUrl);
        // 2. 接受返回数据
        Vector<String> trainResult = trainService.trainFile(trainUrl, paramUrl);
        // 3. 回显数据写入Session
        trainService.setSession(request, trainResult);
        return new Result(Code.TRAIN_OK,"success", null);
    }

    /**
     * 使用参数方式训练
     * 获取表单数据
     * 还没有写完
     */
    @PostMapping("/trainParam")
    public Result trainParam(HttpServletRequest request, @RequestBody BlastParam blastParam) {
        System.out.println("=========Controller：参数指令开始训练，读取Session中文件属性===========");
        // 1. 从Session获取数据
        HttpSession session = request.getSession();
        String trainUrl = (String) session.getAttribute("train_Url");

        System.out.println(blastParam.toString());
        // 2. 接受返回数据
        Vector<String> trainResult = trainService.trainParam(trainUrl, blastParam);
        trainService.setSession(request, trainResult);
        return new Result(Code.TRAIN_OK, "success", null);
    }


}
