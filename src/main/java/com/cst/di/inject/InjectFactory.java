package com.cst.di.inject;

import com.cst.di.mapper.IMapper;

public class InjectFactory {

    public static InjectHandler getInject(IMapper mapper) {
        mapper.configure();
        return new InjectHandler(mapper);
    }

}
